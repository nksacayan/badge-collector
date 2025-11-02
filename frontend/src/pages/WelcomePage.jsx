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
		if (!name.trim()) return;
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

export default WelcomePage;