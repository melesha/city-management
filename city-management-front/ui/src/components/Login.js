import React, {useState, useRef, forwardRef} from "react";
import Form from "react-validation/build/form";
import {Slide, Dialog} from "@mui/material";

import authService from "../services/auth.service";

const Transition = forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />
})

const Login = ({open, onClose}) => {
    const form = useRef();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    };

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };

    const handleLogin = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);

        form.current.validateAll();

        authService.login(username, password)
            .then((response) => {
            if (response && response.data) {
                setLoading(false);
                localStorage.setItem("token", JSON.stringify(response.data));
                localStorage.setItem("user", JSON.stringify(username));
                window.location.reload();
            }
            }).catch((error) => {
                setLoading(false);
                setMessage("Error to Authenticate the user " + username);
                setTimeout(() => {
                    setMessage("");
                }, 3000);
                console.error(error);
            });
    };

    const handleClose = () => {
        console.log("handleClose");
        onClose();
    };

    return (
        <Dialog
            open={open}
            TransitionComponent={Transition}
            keepMounted
            >
            <div className="card-single-container">
                <div className="card">
                <img
                    src="user.png"
                    alt="profile-img"
                    className="profile-img-card"
                />

                    <Form onSubmit={handleLogin} ref={form}>
                        <div className="card-form-group">
                            <label htmlFor="username">Username: </label>
                            <input type="text" value={username} name="username" onChange={onChangeUsername} onKeyDown={(e) => { e.key === 'Enter' && handleLogin(e) }}/>
                        </div>

                        <div className="card-form-group">
                            <label htmlFor="password">Password:  </label>
                            <input type="password" value={password} name="password" onChange={onChangePassword} onKeyDown={(e) => { e.key === 'Enter' && handleLogin(e) }}/>
                        </div>

                        <div className="card-form-group">
                            <button onClick={handleClose}>
                                Cancel
                            </button>
                            <button type="submit" className="sbmt-btn" disabled={loading} >
                                {loading && (
                                    <span className="spinner-border spinner-border-sm"></span>
                                )}
                                <span>Login</span>
                            </button>
                        </div>

                        {message && (
                            <div className="form-group">
                                <div className="card-alert card-alert-danger">
                                    {message}
                                </div>
                            </div>
                        )}
                    </Form>
                </div>
            </div>
        </Dialog>
    );
};

export default Login;
