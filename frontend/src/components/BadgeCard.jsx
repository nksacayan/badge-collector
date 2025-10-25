import './BadgeCard.css';
import { useNavigate } from 'react-router';
import badgePlaceholder from '../assets/Badge_Placeholder.png';

const BadgeCard = ({ badge, isEarned }) => {
	const navigate = useNavigate();
	const handleClick = () => {
		navigate(`/badge/${badge.id}`);
	};

	return (
		// Apparently this stupid div is necessary for img stretching in a grid leave it
		<div className='icon-flex-wrapper'>
			<img className={`${isEarned ? '' : 'faded'}`} onClick={handleClick} src={badgePlaceholder} alt="Badge Icon" />
		</div>
	);
};

export default BadgeCard;