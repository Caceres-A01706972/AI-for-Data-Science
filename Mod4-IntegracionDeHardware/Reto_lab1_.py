from flask import Flask
from flask_restful import Resource, Api, reqparse, abort

import json
import os

app = Flask("CountriesAPI")
api = Api(app)

parser = reqparse.RequestParser()
parser.add_argument('name', required = True)
parser.add_argument('capital', required = True)
parser.add_argument('region', required = True)
parser.add_argument('phone_code', required = True)


# Determine the script's directory
script_directory = os.path.dirname(os.path.abspath(__file__))
# Load videos from JSON file
json_file_path = os.path.join(script_directory, "countries.json")
with open(json_file_path,'r') as f:
    countries = json.load(f)

class AllCountries(Resource):
    def get(self):
        return countries, 200

class Country(Resource):
    def get(self, country_id):
        country_id = int(country_id)  # Convert string to integer
        country = next((c for c in countries if c['id'] == country_id), None)
        if country:
            return country, 200
        else:
            return {"message": "Country not found"}, 404

api.add_resource(AllCountries, "/countries")
api.add_resource(Country, "/countries/<string:country_id>")

if __name__ == "__main__":
    app.run()