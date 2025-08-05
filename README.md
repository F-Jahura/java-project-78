### Hexlet tests and linter status:
[![Actions Status](https://github.com/F-Jahura/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/F-Jahura/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=F-Jahura_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=F-Jahura_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=F-Jahura_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=F-Jahura_java-project-78)
# Валидатор данных
**Описание:**
 
**Валидатор данных** - это инструмент, который проверяет данные на соответствие определённым критериям и правилам до их использования в дальнейшей обработке. Цель валидации — исключить поступление в систему ошибочных, неполных или неточных данных, которые могут привести к ошибочным результатам работы, утрате данных и сбоям в работе систем.

**Пример использования:**

Для использования репозитория через JShell, вам нужно выполнить следующие шаги:
1.  **Запустить JShell:** Запустите интерпретатор JShell, который предоставляет интерактивную среду для выполнения кода на Java.
2.  **Импортировать классы:** Импортируйте необходимые классы из репозитория, чтобы использовать их функциональности в JShell. Например:
    ```
    import hexlet.code.Validator;
    import hexlet.code.schemas.StringSchema;
    import hexlet.code.schemas.NumberSchema;
    import hexlet.code.schemas.MapSchema;
    ```
    
3.  **Создать объект валидатора:** Создайте объект валидатора с помощью конструктора Validator():

    Validator validator = new Validator();

4.  **Настроить схемы валидации:** Настройте схемы валидации, вызвав соответствующие методы объекта валидатора.     

    **Для валидации строк:**

    StringSchema stringSchema = validator.string().required().minLength(4);

    **Для валидации чисел:**

    NumberSchema numberSchema = validator.number().required().positive().range(5, 10);

    **Для валидации объектов типа Map:**

    MapSchema mapSchema = validator.map().required().sizeof(2);

5.  **Проверить данные:** Проверьте корректность данных, вызвав метод isValid() на соответствующей схеме валидации. 