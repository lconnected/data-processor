# data-processor

## Предустановки
Перед запуском приложения нужно создать базу данных `dp_database` и login role `dp_user`.

### Сборка
Рекомендуется использовать gradle-wrapper, который доступен через скрипт `gradlew.bat` или `gradlew`  
Для сборки выполнить `./gradlew clean build`  
Если требуется пропустить тесты, то `./gradlew clean build -X test`  
Результирующий jar лежит в `build/libs/`
