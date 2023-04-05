import React, {useState, useEffect} from "react";
import './App.css';

import AuthService from "./services/auth.service";
import apiService from "./services/api.service";

import Cities from "./components/Cities";
import Pagination from "./components/Pagination"
import Filter from "./components/Filter"
import Login from "./components/Login"

function App() {
  const [cities, setCities] = useState([]);
  const [city, setCity] = useState("");
  const [loading, setLoading] = useState(false);
  const [pageNumber, setPageNumber] = useState(0);
  const [pageSize, setPageSize] = useState(30);
  const [totalPages, setTotalPages] = useState(0);
  const [currentUser, setCurrentUser] = useState(undefined);
  const [toggleLogin, setToggleLogin] = useState(false);
  const [message, setMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const getCities = (cityName, pageSelected) => {

    setLoading(true);

    apiService.getCities(cityName, pageSelected, pageSize)
        .then(res => {
          console.log(res);
          setCities(res.data.content);
          setTotalPages(res.data.totalPages);
        });

    setLoading(false);
  };

  useEffect(() => {
    const user = AuthService.getUser();
    setCurrentUser(user);

    getCities(city, pageNumber);

  }, []);

  const paginate = pageSelected => {
    console.log("page Selected: " + pageSelected);
    getCities(city, pageSelected);
    setPageNumber(pageSelected);
  }

  const search = value => {
    console.log("search By : " + value);
    getCities(value, 0);
    setCity(value);
  }

  const logIn = () => {
    setToggleLogin(true);

  }

  const logOut = () => {
    AuthService.logout();
    setCurrentUser(undefined);
  }

  const handleCloseLogin = () => {
    setToggleLogin(false);
  }

  const onCityEvent = (message, errorMessage) => {
    setMessage(message);
    setErrorMessage(errorMessage);
    getCities(city, pageNumber);

    setTimeout(() => {
      setErrorMessage("");
      setMessage("");
    }, 3000);
  }

  return (
    <div className="App">
      <nav>
        {currentUser ? (
            <div className="navbar">
              <li className="nav-item-right">
                <a href="/logout" className="nav-link" onClick={logOut}>
                  LogOut
                </a>
              </li>
              <li className="nav-item-left">
                Hello <b>{currentUser}</b>. Welcome to the City Management Tool.
              </li>
            </div>
        ) : (
            <div className="navbar">
              <li className="nav-item-right">
                <a className="nav-link" onClick={logIn}>
                  Login
                </a>
              </li>
            </div>
        )}
      </nav>
      <Filter search={search}/>
      {errorMessage && (
          <div className="card-top-fixed">
            <div className="card-alert card-alert-danger">
              {errorMessage}
            </div>
          </div>
      )}

      {message && (
          <div className="card-top-fixed">
            <div className="card-alert card-alert-ok">
              {message}
            </div>
          </div>
      )}
      <Cities cities={cities} loading={loading} onEvent={onCityEvent}/>
       <Pagination pages={totalPages} currentPage={pageNumber} paginate={paginate}/>
       {toggleLogin && (
          <Login open={toggleLogin} onClose={handleCloseLogin}/>
       )}
    </div>
  );
}

export default App;
