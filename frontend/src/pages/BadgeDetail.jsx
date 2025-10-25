import { useNavigate, useParams } from 'react-router';
import './BadgeDetail.css';
import badgePlaceholder from '../assets/Badge_Placeholder.png';


const mockBadgeData = {
	1: {
		name: 'KNOT BADGE',
		description:
			'Head to Station 2 and choose a knot to learn. When complete, bring your knot to Nick or Angela to claim your badge.',
	},
	// Add more badge entries here
};

const BadgeDetail = ({ isEarned }) => {
	const navigate = useNavigate();
	const { id } = useParams();
	const badge = mockBadgeData[id] || mockBadgeData[1];

	return (
		<>
			<div className="badge-detail-container">
				{/* Is using navigate here why the scroll is saved? Neat */}
				<button className="back-button common-button" onClick={() => navigate(-1)}>Back</button>
				{isEarned ? <p className='common-button badge-status earned'>EARNED</p> : <p className='common-button badge-status'>NOT EARNED</p>}
				<img id="badge-placeholder-icon" src={badgePlaceholder} alt="Badge Icon" />
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