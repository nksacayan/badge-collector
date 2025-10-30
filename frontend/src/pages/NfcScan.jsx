import { useNavigate, useParams } from 'react-router';
import './BadgeDetail.css';
import { useEffect, useState, useContext } from "react";
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;

const NfcScan = () => {
	const { badgeId } = useParams();
	const [badge, setBadge] = useState(null);
	const navigate = useNavigate();


	useEffect(() => {
		const fetchData = async () => {
			const jwt = localStorage.getItem('badgeCollectorToken');
			const res = await fetch(`${apiUrl}/nfc/add-badge/${encodeURIComponent(badgeId)}`,
				{
					method: 'POST',
					headers: {
						"Content-Type": "text/plain"
					},
					body: jwt
				});
			if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
			const user = await res.json();
			const newBadge = user.badges.find(badge => badge.id === Number(badgeId));
			setBadge(newBadge);
		};
		fetchData();
	}, [badgeId]);

	if (badge) {
		navigate(`/badge/${badgeId}`)
	}
	else {
		return <h2>Scanning...</h2>
	}
};

export default NfcScan;