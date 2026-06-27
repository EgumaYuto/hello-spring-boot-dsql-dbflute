import { Navigate } from 'react-router-dom'
import { useAuth } from '../auth/AuthContext'
import type { ReactNode } from 'react'

// Redirects to /login when there is no authenticated user. While the session is
// being restored (on a page reload) it shows a lightweight loading state.
export function ProtectedRoute({ children }: { children: ReactNode }) {
  const { user, loading } = useAuth()

  if (loading) {
    return <div className="center-page muted">Loading…</div>
  }
  if (!user) {
    return <Navigate to="/login" replace />
  }
  return <>{children}</>
}
