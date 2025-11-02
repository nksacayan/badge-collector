import BadgeGallery from './pages/BadgeGallery';
import WelcomePage from './pages/WelcomePage';
import BadgeDetail from './pages/BadgeDetail';
import Leaderboard from './pages/Leaderboard';
import { BrowserRouter, Routes, Route } from "react-router";
import { UserProvider } from './components/UserProvider';
import NfcScan from './pages/NfcScan';
import NfcScanDev from './pages/NfcScanDev';
const isDevMode = import.meta.env.VITE_DEV_MODE === 'true'; 

function App() {
  return (
    <BrowserRouter>
      <UserProvider>
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/badges" element={<BadgeGallery />} />
          <Route path="/badge/:badgeId" element={<BadgeDetail />} />
          {isDevMode && (
            <Route path="/nfc/:badgeId" element={<NfcScanDev />} />
          )}
          <Route path="/nfc/:badgeId/nfc-id/:nfcId" element={<NfcScan />} />
          <Route path="/leaderboard" element={<Leaderboard />} />
        </Routes>
      </UserProvider>
    </BrowserRouter>
  );
}

export default App;