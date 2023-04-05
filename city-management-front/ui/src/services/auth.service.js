import axios from "axios";

const AUTH_URL = "http://localhost:8181/city-management/token"

const login = (username, password) => {
   return  axios
        .post(AUTH_URL, {},{
            auth: {
                username: username,
                password: password
            }
        });

};

const logout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
};

const getUser = () => {
    return JSON.parse(localStorage.getItem("user"))
};

const getAuthHeader = () => {
    const user = JSON.parse(localStorage.getItem('token'));

    if (user) {
        return { Authorization: 'Bearer ' + user };
    } else {
        return {};
    }
}


const AuthService = {
    login,
    logout,
    getUser,
    getAuthHeader
};

export default AuthService;
