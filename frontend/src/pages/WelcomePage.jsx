import { useState, useContext } from 'react';
import { useNavigate } from "react-router";
import { UserContext } from '../components/UserContext';
import './WelcomePage.css';
import logo from '../assets/Scout_Logo.png';
import charmander from '../assets/tracing_charmander_square.JPG';
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;

const WelcomePage = () => {
	const [name, setName] = useState('');
	const navigate = useNavigate();
	const { setUser } = useContext(UserContext);

	const handleSubmit = async () => {
		if (!name.trim()) return;
		try {
			const res = await fetch(`${apiUrl}/user?name=${encodeURIComponent(name)}`);
			if (res.ok) {
				const user = await res.json();
				if (user?.id) {
					setUser(user);
				} else {
					throw new Error('User not found');
				}
			} else {
				const createRes = await fetch(`{apiUrl}/user/create?name=${encodeURIComponent(name)}`);
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

			<img id="cat-icon" src={charmander} alt="Icon" />

			<div className="name-input-group">
				<input
					id="name-input"
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