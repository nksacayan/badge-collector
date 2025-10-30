import { useNavigate, useParams } from 'react-router';
import './BadgeDetail.css';
import { useEffect, useState, useContext } from "react";
import { UserContext } from "../components/UserContext";
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;

const NfcScan = () => {
	const { badgeId } = useParams();
	// I don't think context works since nfc manually navigates to url but try it out
	const context = useContext(UserContext);
	const [badge, setBadge] = useState(null);

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
			if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
			const user = await res.json();
			const newBadge = user.badges.find(badge => badge.id === Number(badgeId));
			setBadge(newBadge);
		};
		fetchData();
	}, [badgeId]);

	if (badge) {
		return <h2>Scanned nfc for {badge.name}</h2>
	}
	else {
		return <h2>Scanning...</h2>
	}
};

export default NfcScan;