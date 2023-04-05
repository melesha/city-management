import axios from "axios";
import authService from "./auth.service";

const API_URL = "http://localhost:8181/city-management/cities"

const getCities = (cityName, pageNumber, pageSize) => {

    const queryParams = {
        params: {
            pageSize: pageSize,
            pageNumber: pageNumber
        }
    };

    if (cityName && !cityName.isEmpty) {
        queryParams.params.cityName = cityName;
    }

    return axios.get(API_URL, queryParams);
}
const getCity = (id) => {
    return axios.get(API_URL + "/" +id, { headers: authService.getAuthHeader() });
}

const updateCity = (id, cityName, photo) => {
    const data =
        {
            id: id,
            name: cityName,
            photo: photo
        }
    ;

    return axios.put(API_URL, data, {headers: authService.getAuthHeader() });
}

const ApiService = {
    getCities,
    getCity,
    updateCity
};

export default ApiService;
