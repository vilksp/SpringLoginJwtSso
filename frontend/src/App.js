import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Homepage from "./components/Homepage";
import Login from "./components/login-component/Login";

function App() {
	return (
		<div className="App">
			<Router>
				<Route exact path="/">
					<Homepage />
				</Route>
				<Route exact path="/login">
					<Login />
				</Route>
			</Router>
		</div>
	);
}

export default App;
