Setup frontend proxy to hit backend 


---
Use token-based auth and avoid cookies so i can avoid https

✅ Party-safe setup (no HTTPS, no CA installs)
Here’s how to make it work cleanly across all LAN devices:
🔐 Use token-based auth
- Issue a short-lived token from the backend (e.g., /api/login)
- Store it in memory or localStorage
- Send it in the Authorization header:
fetch('/api/user', {
  headers: { Authorization: `Bearer ${token}` },
  credentials: 'omit'
});


🧭 Use Vite proxy for same-origin dev
- Proxy /api to Spring Boot so frontend and backend appear same-origin
- No CORS issues, no cookie weirdness
🌐 Serve frontend from Windows LAN IP
- Vite config:
server: {
  host: '0.0.0.0',
  port: 3000,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true,
      secure: false,
    },
  },
}


- Access from mobile: http://192.168.1.69:3000
🧪 Backend CORS config
Spring Boot:
.allowedOriginPatterns("http://192.168.*.*")
.allowCredentials(false) // since you're not using cookies



🎉 Party-proof checklist
- ✅ No HTTPS needed
- ✅ No CA installs
- ✅ No cookies required
- ✅ Works on any LAN device with a browser
- ✅ Easy to reset or rotate tokens if needed

---

For NFC token:

🧩 Options to inject identity into NFC-triggered requests
✅ Option 1: Use a frontend redirector
NFC tag points to frontend, not backend:
http://192.168.1.69:3000/scan?badgeId=XYZ123


Your frontend:
- Reads badgeId from the URL
- Checks if the user is logged in (token in memory or localStorage)
- Sends a fetch to the backend with Authorization: Bearer <token> and badgeId
This way, the browser injects the token because it’s running your app.
Pros:
- Works with your existing token-based auth
- No need to modify NFC tags later
- You control the UX (can show feedback, errors, etc.)
Cons:
- Requires users to be logged in before scanning
- If they scan from outside your app, it won’t work


---

🔧 Why JWT Wins for Your Use Case
You're building a LAN-hosted party site with:
- React frontend that needs to know who's logged in
- NFC-triggered endpoints that must include user identity
- No serious security concerns (so token storage in localStorage is fine)
- A desire for modular, reusable patterns
JWT is the ergonomic choice because:
- You can embed user info directly in the token (sub, name, role, etc.).
- React can decode the token without extra API calls.
- NFC scans can hit endpoints with Authorization: Bearer <token> — no session context needed.
- You avoid session store complexity and scale cleanly even if you expand later.

🚀 How JWT Works in Your App
- Login: User logs in via React frontend.
- Token Issued: Spring Boot backend generates a JWT and sends it to the client.
- Storage: Client stores the token (e.g., in memory or localStorage).
- Authenticated Requests: Client includes the token in Authorization: Bearer <token> header.
- Validation: Backend verifies the token signature and extracts user info.

✅ Benefits for Your Use Case
- Stateless: No need to store sessions server-side—great for LAN.
- Fast: Token validation is quick and scalable.
- Secure Identity Transfer: NFC-triggered requests can include the JWT to identify the user.
- Portable: Can be passed via headers, cookies, or even URLs (Base64Url-encoded)

⚠️ Things to Watch Out For
- Token Expiry: Use short-lived tokens with exp claim.
- Revocation: JWTs are hard to revoke unless you track them (e.g., with a blacklist).
- Storage Risks: Avoid storing tokens in localStorage if XSS is a concern—consider httpOnly cookies.

🛠️ Best Practices
- Use Spring Security with a JWT filter to validate tokens.
- In React, manage token lifecycle with a custom hook or context.
- Consider refresh tokens for longer sessions.

Want a validator-style JWT filter for Spring Boot or a React hook to manage tokens? I can sketch one out for you.
Sources:

---

To set up a frontend endpoint, you're essentially creating a **route in your frontend app** that responds to a specific URL—like the one encoded in your NFC tag. Here's how to architect it modularly in React (or any SPA framework):

---

### 🧱 Step-by-Step: Frontend Endpoint Setup (React Example)

#### 1. **Define the Route**
In your router config (e.g., using React Router):

```tsx
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import NfcEntryPage from './pages/NfcEntryPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/nfc-entry" element={<NfcEntryPage />} />
        {/* other routes */}
      </Routes>
    </Router>
  );
}
```

This makes `https://yourdomain.com/nfc-entry` a valid frontend endpoint.

---

#### 2. **Create the Page Component**
This component handles the NFC-triggered logic:

```tsx
// pages/NfcEntryPage.tsx
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export default function NfcEntryPage() {
  const navigate = useNavigate();

  useEffect(() => {
    const jwt = localStorage.getItem('jwt');
    if (!jwt) {
      navigate('/login');
      return;
    }

    fetch('/api/nfc-scan', {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${jwt}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ scannedAt: Date.now() })
    }).then(res => {
      if (res.ok) navigate('/welcome');
      else navigate('/error');
    });
  }, []);

  return <div>Processing NFC scan…</div>;
}
```

---

#### 3. **Deploy Your Frontend**
- Host it on a domain like `lanhub.gg`
- Ensure your NFC tag points to `https://lanhub.gg/nfc-entry`

---

### 🧠 Validator Routine Summary

| Step | Purpose | Example |
|------|---------|---------|
| Define route | Make URL valid | `/nfc-entry` |
| Create page | Handle JWT + backend | `NfcEntryPage.tsx` |
| Deploy | Make it reachable | `https://lanhub.gg/nfc-entry` |

---

Want to modularize this into a reusable pattern for other NFC flows like check-in, guest login, or device pairing? I can help you scaffold that next.
