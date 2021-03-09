import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Homepage from "./components/Homepage";
import Login from "./components/login-component/Login";
import Register from "./components/register-component/Register";
import Header from "./components/header/header";

function App() {
	return (
		<div className="App">
			<Router>
				<Header />
				<Route exact path="/">
					<Homepage />
				</Route>
				<Route exact path="/login">
					<Login />
				</Route>
				<Route exact path="/register">
					<Register />
				</Route>
			</Router>
		</div>
	);
}

export default App;
