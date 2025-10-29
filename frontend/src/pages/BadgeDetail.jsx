import { useNavigate, useParams } from 'react-router';
import './BadgeDetail.css';
import badgePlaceholder from '../assets/Badge_Placeholder.png';
import { useEffect, useState } from "react";
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;


const BadgeDetail = () => {
	const navigate = useNavigate();
	const [badge, setBadge] = useState([]);
	const [ownsBadge, setOwnsBadge] = useState(false);
	const { badgeId } = useParams();

	useEffect(() => {
		const fetchData = async () => {
			if (!badgeId) return; // Skip if badgeId is undefined
			// Do more error handling here later

			try {
				const response = await fetch(`${apiUrl}/badge/${encodeURIComponent(badgeId)}`);
				if (!response.ok) throw new Error("Failed to fetch badge");

				const badgeJson = await response.json();
				setBadge(badgeJson);

				const hasBadge = badgeJson.users?.some(user => user.id === Number(badgeId));
				hasBadge ? setOwnsBadge(true) : setOwnsBadge(false);
			} catch (err) {
				console.error("Error fetching badge:", err);
			}
		};

		fetchData();
	}, [badgeId]);

	return (
		<>
			<div className="badge-detail-container">
				{/* Is using navigate here why the scroll is saved? Neat */}
				<button className="back-button common-button" onClick={() => navigate(-1)}>Back</button>
				{ownsBadge ? <p className='common-button badge-status earned'>EARNED</p> : <p className='common-button badge-status'>NOT EARNED</p>}
				<img src={badgePlaceholder} alt="Badge Icon" />
				<h1 className="badge-name">{badge.name}</h1>
				<p className="badge-description">{badge.description}</p>
				{/* Keep navigate here since badges will be dynamic paths */}
			</div>
			<footer className='badge-nav-button-footer'>
				<button className="badge-nav-button" onClick={() => navigate(-1)}>&#60; Previous</button>
				<button className="badge-nav-button" onClick={() => navigate(-1)}>Next &#62;</button>
			</footer>
		</>
	);
};

export default BadgeDetail;