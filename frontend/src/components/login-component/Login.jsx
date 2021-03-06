import React, { useState, useEffect } from "react";
import { Link, Redirect } from "react-router-dom";
import { isTokenValid, login } from "../services/auth-service";
import "./login.css";
function Login() {
	const [details, setDetails] = useState({ username: "", password: "" });

	const submitHandler = (e) => {
		if (details.username !== "" && details.password !== "") {
			login(details);
		}
	};
	useEffect(() => {
		if (isTokenValid()) {
			window.location.replace("/");
		}
	});

	return (
		<div className="login-box">
			<h1>Login</h1>
			<div className="text-box">
				<input
					type="username"
					placeholder="Username"
					onChange={(e) => setDetails({ ...details, username: e.target.value })}
					value={details.username}
				></input>
			</div>
			<div className="text-box">
				<input
					type="password"
					placeholder="Password"
					onChange={(e) => setDetails({ ...details, password: e.target.value })}
					value={details.password}
				></input>
			</div>
			<input
				className="btn"
				type="button"
				value="Sign in"
				onClick={submitHandler}
			/>
			<button className="btn">
				<a href="http://localhost:8080/oauth2/authorization/github">
					Login with github
				</a>
			</button>
			<Link to="/register">
				<button className="btn">Don't have an account? Register here</button>
			</Link>
		</div>
	);
}

export default Login;
