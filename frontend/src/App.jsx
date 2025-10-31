import BadgeGallery from './pages/BadgeGallery';
import WelcomePage from './pages/WelcomePage';
import BadgeDetail from './pages/BadgeDetail';
import Leaderboard from './pages/Leaderboard';
import { BrowserRouter, Routes, Route } from "react-router";
import { UserProvider } from './components/UserProvider';
import NfcScan from './pages/NfcScan';

function App() {
  return (
    <BrowserRouter>
      <UserProvider>
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/badges" element={<BadgeGallery />} />
          <Route path="/badge/:badgeId" element={<BadgeDetail />} />
          <Route path="/nfc/:badgeId" element={<NfcScan />} />
          <Route path="/leaderboard" element={<Leaderboard />} />
        </Routes>
      </UserProvider>
    </BrowserRouter>
  );
}

export default App;