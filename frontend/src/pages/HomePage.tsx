import { useEffect, useState, type FormEvent } from 'react'
import { api, type Todo } from '../api/client'
import { useAuth } from '../auth/AuthContext'

export function HomePage() {
  const { user, logout } = useAuth()
  const [todos, setTodos] = useState<Todo[]>([])
  const [title, setTitle] = useState('')
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(true)
  const [adding, setAdding] = useState(false)

  useEffect(() => {
    api.todos
      .list()
      .then(setTodos)
      .catch((err) => setError(err instanceof Error ? err.message : 'Failed to load todos'))
      .finally(() => setLoading(false))
  }, [])

  const addTodo = async (e: FormEvent) => {
    e.preventDefault()
    const text = title.trim()
    if (!text) return
    setAdding(true)
    setError(null)
    try {
      const created = await api.todos.create(text)
      setTodos((prev) => [...prev, created])
      setTitle('')
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to add todo')
    } finally {
      setAdding(false)
    }
  }

  const toggle = async (todo: Todo) => {
    try {
      const updated = await api.todos.setDone(todo.id, !todo.done)
      setTodos((prev) => prev.map((t) => (t.id === updated.id ? updated : t)))
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to update todo')
    }
  }

  const remove = async (todo: Todo) => {
    try {
      await api.todos.remove(todo.id)
      setTodos((prev) => prev.filter((t) => t.id !== todo.id))
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to delete todo')
    }
  }

  const remaining = todos.filter((t) => !t.done).length

  return (
    <>
      <div className="topbar">
        <strong>My TODOs</strong>
        <div>
          <span className="muted" style={{ marginRight: '1rem' }}>
            {user?.name}
          </span>
          <button onClick={logout}>ログアウト</button>
        </div>
      </div>
      <div className="content">
        <h1>やること</h1>
        <form onSubmit={addTodo} className="todo-add">
          <input
            type="text"
            placeholder="新しいタスクを入力…"
            value={title}
            maxLength={500}
            onChange={(e) => setTitle(e.target.value)}
          />
          <button type="submit" disabled={adding || title.trim() === ''}>
            追加
          </button>
        </form>
        {error && <div className="error">{error}</div>}
        {loading ? (
          <p className="muted">読み込み中…</p>
        ) : todos.length === 0 ? (
          <p className="muted">まだタスクがありません。上から追加してください。</p>
        ) : (
          <>
            <ul className="todo-list">
              {todos.map((t) => (
                <li key={t.id} className={t.done ? 'done' : ''}>
                  <label>
                    <input type="checkbox" checked={t.done} onChange={() => toggle(t)} />
                    <span>{t.title}</span>
                  </label>
                  <button className="link-danger" onClick={() => remove(t)} aria-label="削除">
                    削除
                  </button>
                </li>
              ))}
            </ul>
            <p className="muted">残り {remaining} 件</p>
          </>
        )}
      </div>
    </>
  )
}
