import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// Dev server runs on :5173 and proxies /api -> Spring Boot on :8080, so the
// browser only ever talks to one origin (no CORS needed in normal local use).
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
})
