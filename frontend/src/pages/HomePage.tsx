import { useEffect, useState } from 'react'
import { api, type User } from '../api/client'
import { useAuth } from '../auth/AuthContext'

export function HomePage() {
  const { user, logout } = useAuth()
  const [users, setUsers] = useState<User[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    api
      .users()
      .then(setUsers)
      .catch((err) => setError(err instanceof Error ? err.message : 'Failed to load users'))
      .finally(() => setLoading(false))
  }, [])

  return (
    <>
      <div className="topbar">
        <strong>Hello DSQL</strong>
        <div>
          <span className="muted" style={{ marginRight: '1rem' }}>
            {user?.name}（{user?.email}）
          </span>
          <button onClick={logout}>ログアウト</button>
        </div>
      </div>
      <div className="content">
        <h1>登録ユーザー一覧</h1>
        <p className="muted">
          このページはログイン必須です。一覧は Aurora DSQL 互換の users テーブルから
          DBFlute 経由で取得しています。
        </p>
        {error && <div className="error">{error}</div>}
        {loading ? (
          <p className="muted">読み込み中…</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>名前</th>
                <th>メールアドレス</th>
              </tr>
            </thead>
            <tbody>
              {users.map((u) => (
                <tr key={u.id}>
                  <td>{u.name}</td>
                  <td>{u.email}</td>
                </tr>
              ))}
              {users.length === 0 && (
                <tr>
                  <td colSpan={2} className="muted">
                    ユーザーがいません
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        )}
      </div>
    </>
  )
}
