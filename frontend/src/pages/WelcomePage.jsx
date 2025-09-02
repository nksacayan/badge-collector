import { useState } from 'react';
import { useNavigate } from "react-router";
import './WelcomePage.css';

const WelcomePage = () => {
	const [name, setName] = useState('');
	let navigate = useNavigate();

	const handleSubmit = () => {
		// if (name.trim()) {
		// 	localStorage.setItem('username', name);
		// }
		navigate('/badges');
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