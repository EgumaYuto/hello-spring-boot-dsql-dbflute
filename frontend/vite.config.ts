import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// Dev server runs on :5173 and proxies /api to the backend, so the browser only
// ever talks to one origin (no CORS needed).
//
// The proxy target is configurable via the API_TARGET env var:
//   - unset (default): local Spring Boot at http://localhost:8080  -> `npm run dev`
//   - deployed Lambda Function URL:                                 -> `npm run dev:lambda`
//     (scripts/frontend-lambda.sh fills API_TARGET from terraform output)
const apiTarget = process.env.API_TARGET ?? 'http://localhost:8080'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: apiTarget,
        changeOrigin: true,
        secure: true,
      },
    },
  },
})
