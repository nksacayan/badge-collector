import './BadgeCard.css';
import { useNavigate } from 'react-router';
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;

const BadgeCard = ({ badge }) => {
	const navigate = useNavigate();
	const handleClick = () => {
		navigate(`/badge/${badge.id}`);
	};

	return (
		// Apparently this stupid div is necessary for img stretching in a grid leave it
		<div className='icon-flex-wrapper'>
			<img className={`${badge.owned ? '' : 'faded'}`} onClick={handleClick} src={`${apiUrl}/badges/images/${badge.imageFilename}`} alt={badge.name} loading="lazy" />
		</div>
	);
};

export default BadgeCard;