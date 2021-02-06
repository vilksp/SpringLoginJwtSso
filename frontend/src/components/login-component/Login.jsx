import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./login.css";
function Login() {
	const [details, setDetails] = useState({ username: "", password: "" });
	return (
		<div className="login-box">
			<h1>Login</h1>
			<div className="text-box">
				<input type="text" placeholder="Username" value=""></input>
			</div>
			<div className="text-box">
				<input type="password" placeholder="Password" value=""></input>
			</div>
			<input className="btn" type="button" value="Sign in" />
			<button className="btn">Login with GitHub</button>
			<Link to="/register">
				<button className="btn">Don't have an account? Register here</button>
			</Link>
		</div>
	);
}

export default Login;
