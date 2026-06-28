import { useEffect, useState, type FormEvent } from 'react'
import { api, type Todo, type TodoStatus } from '../api/client'
import { useAuth } from '../auth/AuthContext'

const DONE = 'DONE'

export function HomePage() {
  const { user, logout } = useAuth()
  const [todos, setTodos] = useState<Todo[]>([])
  const [statuses, setStatuses] = useState<TodoStatus[]>([])
  const [title, setTitle] = useState('')
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(true)
  const [adding, setAdding] = useState(false)

  useEffect(() => {
    Promise.all([api.todos.list(), api.todoStatuses()])
      .then(([todoList, statusList]) => {
        setTodos(todoList)
        setStatuses(statusList)
      })
      .catch((err) => setError(err instanceof Error ? err.message : 'Failed to load'))
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

  const changeStatus = async (todo: Todo, status: string) => {
    try {
      const updated = await api.todos.setStatus(todo.id, status)
      setTodos((prev) => prev.map((t) => (t.id === updated.id ? updated : t)))
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to update status')
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

  const remaining = todos.filter((t) => t.status !== DONE).length

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
                <li key={t.id} className={t.status === DONE ? 'done' : ''}>
                  <span className="todo-title">{t.title}</span>
                  <span className="todo-actions">
                    <select value={t.status} onChange={(e) => changeStatus(t, e.target.value)}>
                      {statuses.map((s) => (
                        <option key={s.code} value={s.code}>
                          {s.name}
                        </option>
                      ))}
                    </select>
                    <button className="link-danger" onClick={() => remove(t)} aria-label="削除">
                      削除
                    </button>
                  </span>
                </li>
              ))}
            </ul>
            <p className="muted">未完了 {remaining} 件</p>
          </>
        )}
      </div>
    </>
  )
}
