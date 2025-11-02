import { useState, useContext } from 'react';
import { useNavigate } from "react-router";
import { UserContext } from '../components/UserContext';
import './WelcomePage.css';
import logo from '../assets/Scout_Logo.png';
import bdayCat from '../assets/kitty.png';
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;

const WelcomePage = () => {
	const [name, setName] = useState('');
	const navigate = useNavigate();
	const { setUser } = useContext(UserContext);

	const handleSubmit = async () => {
		const trimmedName = name.trim();
		if (!isValidName(trimmedName)) {
			alert('Please enter a valid name (1–14 characters, letters/numbers/underscores/space only).');
			return;
		}
		try {
			const loginResponse = await fetch(
				`${apiUrl}/user/login/${encodeURIComponent(name)}`,
				{ method: 'POST' });
			if (loginResponse.ok) {
				await handleAuthResponse(loginResponse);
			} else {
				const registerResponse = await fetch(
					`${apiUrl}/user/register/${encodeURIComponent(name)}`,
					{ method: 'POST' });
				if (registerResponse.ok) {
					await handleAuthResponse(registerResponse);
				} else {
					throw new Error('Failed to create user');
				}
			}
			navigate('/badges');
		} catch (err) {
			alert('Error connecting to server.');
		}
	};

	async function handleAuthResponse(authResponse) {
		const authBody = await authResponse.json();

		if (!authBody?.user) {
			throw new Error('User not found');
		}
		setUser(authBody.user);

		if (!authBody?.token) {
			throw new Error('Token not found');
		}
		localStorage.setItem('badgeCollectorToken', authBody.token);
	}

	return (
		<div className='common-flex-container content-center'>
			<img id="title-logo" src={logo} alt="Logo" />

			<img id="cat-icon" src={bdayCat} alt="Icon" />

			<div className="name-input-group">
				<input
					className='common-button'
					id="name-input"
					type="text"
					placeholder="Name"
					value={name}
					onChange={(e) => setName(e.target.value)}
				/>
				<button className="common-button" onClick={handleSubmit}>
					&gt;
				</button>
			</div>
		</div>
	);
};

function isValidName(name) {
	const trimmed = name.trim();
	const minLength = 1;
	const maxLength = 14;
	const validPattern = /^[a-zA-Z0-9_ ]+$/; // Alphanumeric + underscore + space

	return (
		trimmed.length >= minLength &&
		trimmed.length <= maxLength &&
		validPattern.test(trimmed)
	);
}

export default WelcomePage;