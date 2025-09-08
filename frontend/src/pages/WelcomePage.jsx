import { useState, useContext } from 'react';
import { useNavigate } from "react-router";
import { UserContext } from '../components/UserContext';
import './WelcomePage.css';

const WelcomePage = () => {
	const [name, setName] = useState('');
	let navigate = useNavigate();
	const { user, setUser } = useContext(UserContext);

	const handleSubmit = async () => {
		if (!name.trim()) return;
		try {
			// Try to fetch user by name
			const res = await fetch(`http://localhost:8080/api/user?name=${encodeURIComponent(name)}`);

			if (res.ok) {
				const user = await res.json(); // Await the JSON parsing
				if (user && user.id) {         // Optional: check if user object is valid
					setUser(user);
				} else {
					throw new Error('User not found in response');
				}
			} else {
				// If not found, create user
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
			<div className="welcome-icon">
				{/* Replace with actual icon or image later */}
				<span role="img" aria-label="party">🎉</span>
			</div>
			<h1 className="welcome-title">welcum</h1>
			<h2 className="welcome-subtitle">to nick & angela<br />birthday extravaganza</h2>

			<div className="name-input-group">
				<input
					type="text"
					placeholder="name"
					value={name}
					onChange={(e) => setName(e.target.value)}
				/>
				<button onClick={handleSubmit}>&gt;</button>
			</div>
		</div>
	);
};

export default WelcomePage;