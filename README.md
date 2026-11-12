# MegaSoft — JDBC, модуль 6

У цьому домашньому завданні я продовжив працювати з базою MegaSoft з попереднього модуля. Тепер SQL-запити виконуються через Java за допомогою JDBC та бази даних H2.

## Що зроблено

- `Database` — сінглтон. Під час першого звернення створює підключення `Connection` до H2 і повертає його через `getConnection()`.
- `DatabaseInitService` — читає `sql/init_db.sql` і створює таблиці.
- `DatabasePopulateService` — читає `sql/populate_db.sql` і додає дані.
- `DatabaseQueryService` — читає п'ять SQL-файлів з `SELECT` та повертає списки Java-об'єктів, а не просто друкує результат.
- `Main` — викликає всі п'ять методів і показує результати в консолі.

SQL-файли у папці `sql` взято з домашнього завдання модуля 4. Для кожного типу результату створено окремий клас у пакеті `model`.

## Як запустити

Для запуску потрібна Java та Gradle. Найпростіше відкрити проєкт в IntelliJ IDEA. H2 підключається як Gradle-залежність `com.h2database:h2:2.5.252`.

У корені проєкту по черзі виконайте:

```powershell
.\gradlew.bat initDb
.\gradlew.bat populateDb
.\gradlew.bat queryDb
```

Або запустіть класи `DatabaseInitService`, `DatabasePopulateService`, `Main` через IntelliJ у цьому ж порядку.

Після першої команди створюються чотири таблиці, після другої — 12 працівників, 5 клієнтів, 10 проєктів і 29 зв'язків між проєктами та працівниками. Третя команда виводить результати всіх п'яти запитів.

База даних зберігається локально в `data/megasoft.mv.db`. Цю папку не потрібно завантажувати на GitHub.

**Важливо:** файли `init_db.sql` та `populate_db.sql` призначені для запуску на порожній базі. Якщо виконати їх удруге, виникнуть помилки про наявні таблиці або повторювані ID. Для повторного запуску з нуля потрібно закрити всі програми, які використовують базу, та видалити локальну папку `data`.

## Методи DatabaseQueryService

| Метод | Що повертає |
| --- | --- |
| `findMaxSalaryWorkers()` | `List<MaxSalaryWorker>` |
| `findMaxProjectsClient()` | `List<MaxProjectCountClient>` |
| `findLongestProjects()` | `List<LongestProject>` |
| `findYoungestEldestWorkers()` | `List<YoungestEldestWorker>` |
| `printProjectPrices()` | `List<ProjectPrice>` |

Приклад використання:

```java
List<MaxProjectCountClient> clients = new DatabaseQueryService().findMaxProjectsClient();
```

Для виконання запитів використовується `Statement`, для читання результатів — `ResultSet`. Ресурси `Statement` та `ResultSet` закриваються через `try-with-resources`, а спільне підключення зберігається у класі `Database`.
