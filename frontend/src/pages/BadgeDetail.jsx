import { useNavigate, useParams } from 'react-router';
import './BadgeDetail.css';
import { useEffect, useState, useContext } from "react";
import { UserContext } from "../components/UserContext";
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;


const BadgeDetail = () => {
	const navigate = useNavigate();
	const [currentBadge, setCurrentBadge] = useState(null);
	const { badgeId } = useParams();
	const context = useContext(UserContext);
	const { user, setUser } = context;
	const [previousId, setPreviousId] = useState(0);
	const [nextId, setNextId] = useState(0);

	useEffect(() => {
		const fetchData = async () => {
			if (!badgeId) return; // Skip if badgeId is undefined
			// Do more error handling here later

			try {
				const response = await fetch(`${apiUrl}/badges`);
				if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
				const allBadges = await response.json();

				const userResponse = await fetch(`${apiUrl}/user/${user.name}`);
				if (!userResponse.ok) throw new Error(`HTTP error! status: ${userResponse.status}`);
				const userJson = await userResponse.json();
				setUser(userJson);

				// Create a Set of user badge IDs for fast lookup
				const ownedBadgeIds = new Set(userJson.badges.map(b => b.id));

				// Merge, annotate, and filter
				const combinedBadges = allBadges
					.map(badge => ({
						...badge,
						owned: ownedBadgeIds.has(badge.id),
					}))
					.filter(badge => !badge.secret || badge.owned);

				const currentBadgeVar = combinedBadges.find(badge => badge.id === Number(badgeId));
				setCurrentBadge(currentBadgeVar);

				const currentIndex = combinedBadges.findIndex(item => item.id === currentBadgeVar.id);
				setPreviousId(currentBadgeVar.id > 1 ? combinedBadges[currentIndex - 1].id : 0);
				setNextId(currentBadgeVar.id < combinedBadges.length ? combinedBadges[currentIndex + 1].id : 0);
			} catch (err) {
				console.error('Failed to fetch badges:', err);
			}
		};

		fetchData();
	}, [badgeId]);

	if (currentBadge)
		return (
			<>
				<div className="badge-detail-container">
					{/* Is using navigate here why the scroll is saved? Neat */}
					<button className="back-button common-button" onClick={() => navigate('/badges')}>Back</button>
					{currentBadge.owned ? <p className='common-button badge-status earned'>EARNED</p> : <p className='common-button badge-status'>NOT EARNED</p>}
					{currentBadge.owned ? 
						<img className='badge-icon'  src={`${apiUrl}/badges/images/${currentBadge.imageFilename}`} alt={currentBadge.name} loading="lazy" /> : 
						<img className='badge-icon faded' src={`${apiUrl}/badges/images/${currentBadge.imageFilename}`} alt={currentBadge.name} loading="lazy" />
					}
					<h1 className="badge-name">{currentBadge.name}</h1>
					<p className="badge-description">{currentBadge.description}</p>
					{/* Keep navigate here since badges will be dynamic paths */}
				</div>
				<footer className='badge-nav-button-footer'>
					{previousId !== 0
						&& <button className="badge-nav-button" onClick={() => navigate(`/badge/${previousId}`)}>
							&#60; Previous
						</button>}
					{nextId !== 0
						&& <button className="badge-nav-button" onClick={() => navigate(`/badge/${nextId}`)}>
							Next &#62;
						</button>}
				</footer>
			</>
		);
	else
		return (
			<p>Loading I hope...</p>
		);
};

export default BadgeDetail;