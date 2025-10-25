import { useNavigate } from 'react-router';
import './BadgeDetail.css';
import badgePlaceholder from '../assets/Badge_Placeholder.png';

const BadgeDetail = () => {
	const navigate = useNavigate();

	return (
		<>
			<div className="badge-detail-container">
				{/* Is using navigate here why the scroll is saved? Neat */}
				<button className="back-button common-button" onClick={() => navigate(-1)}>Back</button>
				{true ? <p className='common-button badge-status earned'>EARNED</p> : <p className='common-button badge-status'>NOT EARNED</p>}
				<img src={badgePlaceholder} alt="Badge Icon" />
				<h1 className="badge-name">Badge Name</h1>
				<p className="badge-description">Badge description</p>
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