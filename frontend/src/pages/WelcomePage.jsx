import { useState, useContext } from 'react';
import { useNavigate } from "react-router";
import { UserContext } from '../components/UserContext';
import './WelcomePage.css';
import logo from '../assets/Scout_Logo.png';

const WelcomePage = () => {
	const [name, setName] = useState('');
	const navigate = useNavigate();
	const { setUser } = useContext(UserContext);

	const handleSubmit = async () => {
		if (!name.trim()) return;
		try {
			const res = await fetch(`http://localhost:8080/api/user?name=${encodeURIComponent(name)}`);
			if (res.ok) {
				const user = await res.json();
				if (user?.id) {
					setUser(user);
				} else {
					throw new Error('User not found');
				}
			} else {
				const createRes = await fetch(`http://localhost:8080/api/user/create?name=${encodeURIComponent(name)}`);
				if (createRes.ok) {
					const newUser = await createRes.json();
					setUser(newUser);
				} else {
					throw new Error('Failed to create user');
				}
			}
			navigate('/badges');
		} catch (err) {
			alert('Error connecting to server.');
		}
	};

	return (
		<div className="welcome-container">
			<img id="title-logo" src={logo} alt="Logo" />

			<div className="illustration-placeholder">
				cutie cat illustration here<br />(still need to draw – will provide as PNG)
			</div>

			<div className="name-input-group">
				<input
					type="text"
					placeholder="Name"
					value={name}
					onChange={(e) => setName(e.target.value)}
				/>
				<button onClick={handleSubmit}>
					&gt;
				</button>
			</div>
		</div>
	);
};

export default WelcomePage;