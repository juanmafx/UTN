package io.github.juanmafx.utn.education;

public record StudentFormState(boolean fieldsEnabled, boolean newEnabled, boolean saveEnabled, boolean cancelEnabled) {
    public static StudentFormState idle() {
        return new StudentFormState(false, true, false, false);
    }

    public StudentFormState startEditing() {
        if (!newEnabled) {
            throw new IllegalStateException("form is already being edited");
        }
        return new StudentFormState(true, false, true, true);
    }

    public StudentFormState finish() {
        if (!fieldsEnabled) {
            throw new IllegalStateException("form is not being edited");
        }
        return idle();
    }
}
