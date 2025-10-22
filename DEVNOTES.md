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