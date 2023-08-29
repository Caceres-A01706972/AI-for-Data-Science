from flask import Flask, render_template, request, jsonify
from flask_restful import Resource, Api, reqparse
from forex_python.converter import CurrencyRates

import json

app = Flask("CountryAPI")
api = Api(app)

# Load countries from JSON file
# Load countries from JSON file with explicit encoding
with open('countries.json', 'r', encoding='utf-8') as f:
    countries = json.load(f)


class AllCountries(Resource):
    def get(self):
        return countries, 200

class Country(Resource):
    def get(self, country_param):
        country_name = country_param.replace("_", " ")  # Replace underscores with spaces
        country = next((c for c in countries if c['name'] == country_name), None)
        if country:
            return country, 200
        else:
            return {"message": "Country not found"}, 404
        
class CurrencyExchange(Resource):
    def get(self, country_param):
        country_name = country_param.replace("_", " ")
        country = next((c for c in countries if c['name'] == country_name), None)
        if country:
            currency_code = country['currency']
            currency_name = country['currency_name']
            
            # Use CurrencyRates to get exchange rate for USD to country's currency
            c = CurrencyRates()
            exchange_rate = c.get_rate("USD", currency_code)
            
            # Calculate the equivalent of 10 USD in the country's currency
            equivalent_in_currency = 10 * exchange_rate
            
            response_message = f"The currency of {country_name} is {currency_name}."
            response_message += f" 10 USD is approximately {equivalent_in_currency:.2f} {currency_code}."
            
            return {"message": response_message}, 200
        else:
            return {"message": "Country not found"}, 404

@app.route("/", methods=["GET"])
def index():
    return render_template("index.html")

@app.route("/search/<string:country_param>", methods=["GET"])
def search_country(country_param):
    country_name = country_param.replace("_", " ")
    country = next((c for c in countries if c['name'] == country_name), None)
    if country:
        currency_code = country['currency']
        currency_name = country['currency_name']

        c = CurrencyRates()
        try:
            exchange_rate = c.get_rate("USD", currency_code)
            equivalent_in_currency = 10 * exchange_rate #Para saber cuanto son 10 dolares en esa moneda

            result = f"The currency of {country_name} is {currency_name}."
            result += f" 10 USD is approximately {equivalent_in_currency:.2f} {currency_code}."
        except:
            result = f"Currency information for {country_name} is not available."

    else:
        result = "Country not found"

    return jsonify({"message": result})

api.add_resource(AllCountries, "/countries")
api.add_resource(Country, "/countries/<string:country_param>")  
api.add_resource(CurrencyExchange, "/currency/<string:country_param>")  

if __name__ == "__main__":
    app.run()
