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
