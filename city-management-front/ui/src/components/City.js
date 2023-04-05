import React, {forwardRef, useEffect, useState} from 'react';
import {Dialog, Slide} from "@mui/material";
import apiService from "../services/api.service";

const Transition = forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />
})
const City = ({id, name, photo, open, onClose}) => {

    const [cityName, setCityName] = useState("");
    const [cityPhoto, setCityPhoto] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        setCityName(name)
        setCityPhoto(photo);
    },[name, photo]);
    const onNameChangeHandler = (event) => {
        setCityName(event.target.value);
    }
    const onPhotoChangeHandler = (event) => {
        setCityPhoto(event.target.value);
    }

    const onCloseHandler = (message) => {
        onClose(message);
    }
    const updateCity = () => {
        apiService.updateCity(id, cityName, cityPhoto)
            .then((response) => {
                if (response && response.status === 200) {
                    onCloseHandler("City was successfully updated");
                }
            })
            .catch((error) => {
                let message = "Something went wrong. Please try later";
                if(error && error.response) {
                     if (error.response.status === 403) {
                        message = "The user is not authorized to update the city.";
                    } else if (error.response.status === 400) {
                         message = "The city name size should be in between 1 and 200 chars. The photo url size should be in between 1 and 1000 chars."
                     }
                }
                setErrorMessage(message);
                setTimeout(() => {
                    setErrorMessage("");
                }, 3000);
                console.error('Error on city update: ' + error);
            });

    }

    return (
        <Dialog
            open={open}
            TransitionComponent={Transition}
            keepMounted
        >
            <div className="card-single-container">
                <div className="card">
                    {cityPhoto ? ( <img src={cityPhoto} alt="City"/>) : ( <img src={photo} alt="City"/>)}
                    {cityName ? (<h2>{cityName}</h2>) : (<h2>{name}</h2>)}
                    <div>
                        <label>Name:</label>
                        <input value={cityName} onChange={onNameChangeHandler} name="cityName" type="text" placeholder="New City Name..."/>
                    </div>
                    <div>
                        <label>Photo:</label>
                        <input value={cityPhoto} onChange={onPhotoChangeHandler} name="cityPhoto" type="text" placeholder="New Photo URL..."/>
                    </div>
                    <div>
                        <button onClick={() => onCloseHandler("")}>Cancel</button>
                        <button className="sbmt-btn" onClick={updateCity}>Save</button>
                    </div>
                </div>
            </div>
            {errorMessage && (
                <div className="form-group" >
                    <div className="card-alert card-alert-danger">
                        {errorMessage}
                    </div>
                </div>
            )}

        </Dialog>
    );
};

export default City;
