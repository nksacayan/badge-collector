import BadgeGallery from './pages/BadgeGallery';
import WelcomePage from './pages/WelcomePage';
import { BrowserRouter, Routes, Route } from "react-router";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<WelcomePage />} />
        <Route path="/badges" element={<BadgeGallery />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;