import React, {useState} from 'react';

import City from "../components/City";
import apiService from "../services/api.service";

const Cities = ({cities, loading, onEvent}) => {

    const [toggleEditCity, setToggleEditCity] = useState(false);
    const [id, setId] = useState("");
    const [cityName, setCityName] = useState("");
    const [cityPhoto, setCityPhoto] = useState("");
    const [message, setMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const editCityHandler = (event) => {
        apiService.getCity(event.target.value).then((response) => {
            if (response.data) {
                setCityName(response.data.name);
                setCityPhoto(response.data.photo);
                setId(event.target.value);
                setToggleEditCity(true);
            }
        }).catch((error) => {
            let errorMessage = "Something went wrong. Please try later";
            if(error && error.response) {
                if (error.response.status === 401) {
                    errorMessage = "You need to log in to edit the city.";
                } else if (error.response.status === 403) {
                    errorMessage = "The user is not authorized to do this action. Please Log In with proper user and try again.";
                }
            }
            setErrorMessage(errorMessage);
            onEvent(message, errorMessage);
            console.error('Error on Authentication: ' + error);
        });

    }
    const handleCloseEditCity = (updMessage) => {
        setToggleEditCity(false);
        setMessage(updMessage);
        onEvent(updMessage, errorMessage);
    }

    if (loading) {
        return <h2>Loading...</h2>;
    }

    return (
        <div>
            <div className="card-group mb4">
                {cities.map(city => (
                    <div className="card" key={city.id}>
                        <img src={city.photo} alt="City photo" defaultValue="//ssl.gstatic.com/accounts/ui/avatar_2x.png"/>
                        <h2>{city.name}</h2>
                        <p>
                            <button value={city.id} onClick={editCityHandler}>Edit</button>
                        </p>
                    </div>
                ))}
                <City id={id} name={cityName} photo={cityPhoto} open={toggleEditCity} onClose={handleCloseEditCity}/>
            </div>
        </div>
    );
};

export default Cities;
