package behaviour_patters.memento

fun main() {
    val editor = Editor()
    val history = History()

    editor.content = "a"
    history.push(editor.createState())

    editor.content = "b"
    history.push(editor.createState())

    println(editor.content)

    editor.content = "c"
    editor.restoreState(history.pop())

    editor.restoreState(history.pop())

    println(editor.content)

}


data class EditorState(val content: String?)


class Editor(var content: String? = null) {
    fun createState(): EditorState {
        return EditorState(content)
    }

    fun restoreState(state: EditorState) {
        content = state.content
    }
}


class History {
    private val states: ArrayList<EditorState> = ArrayList()
    fun push(editorState: EditorState) {
        states.add(editorState)
    }

    fun pop(): EditorState {
        if (states.lastIndex > -1) {
            val lastState = states[states.lastIndex]
            states.remove(lastState)
            return lastState
        } else
            throw Exception("History is empty")
    }
}