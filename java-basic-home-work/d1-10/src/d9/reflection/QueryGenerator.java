package d9.reflection;

import d9.reflection.anotation.Column;
import d9.reflection.anotation.Table;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class QueryGenerator {
    private enum FieldsGetMode {
        WITH_PK, WITHOUT_PK, PK_ONLY
    }

    private String getTable(Class<?> clazz) {
        Table annotation = clazz.getAnnotation(Table.class);

        if (annotation == null) {
            throw new IllegalArgumentException("@Table is missing");
        }

        return annotation.name().isEmpty() ? clazz.getName() : annotation.name();
    }

    private String getFields(Class<?> clazz, FieldsGetMode fieldsGetMode) {
        StringJoiner stringJoiner = new StringJoiner(", ");

        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                if (columnAnnotation.isPk() && fieldsGetMode.equals(FieldsGetMode.WITHOUT_PK)) {
                    continue;
                } else if (!columnAnnotation.isPk() && fieldsGetMode.equals(FieldsGetMode.PK_ONLY)) {
                    continue;
                }

                String columnName = columnAnnotation.name().isEmpty() ? declaredField.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);

            }
        }
        return stringJoiner.toString();
    }

    private String getFieldValues(Object object, FieldsGetMode fieldsGetMode, boolean withName) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        StringJoiner stringJoiner = new StringJoiner(", ");

        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                if (columnAnnotation.isPk() && fieldsGetMode.equals(FieldsGetMode.WITHOUT_PK)) {
                    continue;
                } else if (!columnAnnotation.isPk() && fieldsGetMode.equals(FieldsGetMode.PK_ONLY)) {
                    continue;
                }


                declaredField.setAccessible(true);
                Object columnValue = declaredField.get(object);
                declaredField.setAccessible(false);
                String columnName = "";
                if (withName) {
                    columnName = (columnAnnotation.name().isEmpty() ? declaredField.getName() : columnAnnotation.name()) + " = ";
                }
                stringJoiner.add(columnName + (declaredField.getType().isAssignableFrom(String.class) ? "\"" + columnValue.toString() + "\"" : columnValue.toString()));

            }
        }
        return stringJoiner.toString();
    }

    public String getAll(Class<?> clazz) {
        String tableName = getTable(clazz);
        String fields = getFields(clazz, FieldsGetMode.WITH_PK);

        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        stringBuilder.append(fields);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    public String insert(Object value) throws IllegalAccessException {
        Class<?> clazz = value.getClass();
        String tableName = getTable(clazz);
        String fields = getFields(clazz, FieldsGetMode.WITH_PK);
        String values = getFieldValues(value, FieldsGetMode.WITH_PK, false);

        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(tableName);
        stringBuilder.append("(");
        stringBuilder.append(fields);
        stringBuilder.append(") VALUES (");
        stringBuilder.append(values);
        stringBuilder.append(");");

        return stringBuilder.toString();

    }

    public String update(Object value) throws IllegalAccessException {

        Class<?> clazz = value.getClass();
        String tableName = getTable(clazz);
        String fieldsAndValues = getFieldValues(value, FieldsGetMode.WITHOUT_PK, true);
        String pk = getFieldValues(value, FieldsGetMode.PK_ONLY, true);

        StringBuilder stringBuilder = new StringBuilder("UPDATE ");
        stringBuilder.append(tableName);
        stringBuilder.append(" SET ");
        stringBuilder.append(fieldsAndValues);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(pk);
        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    public String getById(Class clazz, Object id) {

        String tableName = getTable(clazz);
        String fields = getFields(clazz, FieldsGetMode.WITH_PK);
        String pk = getFields(clazz, FieldsGetMode.PK_ONLY);

        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        stringBuilder.append(fields);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(pk+" = "+id.toString());
        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    public String delete(Class clazz, Object id) {

        String tableName = getTable(clazz);
        String pk = getFields(clazz, FieldsGetMode.PK_ONLY);

        StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(pk+" = "+id.toString());
        stringBuilder.append(";");

        return stringBuilder.toString();
    }
}
