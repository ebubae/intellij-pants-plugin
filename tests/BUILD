junit_tests(
    name = 'all',
    dependencies = [
        ':integration',
        ':py-integration',
    ],
    sources = globs(
        'com/twitter/intellij/pants/completion/*.java',
        'com/twitter/intellij/pants/components/impl/*.java',
        'com/twitter/intellij/pants/execution/*.java',
        'com/twitter/intellij/pants/highlighting/*.java',
        'com/twitter/intellij/pants/macro/*.java',
        'com/twitter/intellij/pants/model/*.java',
        'com/twitter/intellij/pants/psi/reference/*.java',
        'com/twitter/intellij/pants/quickfix/*.java',
        'com/twitter/intellij/pants/resolve/*.java',
        'com/twitter/intellij/pants/service/project/*.java',
        'com/twitter/intellij/pants/service/util/*.java',
        'com/twitter/intellij/pants/util/*.java',
    )
)

junit_tests(
    name = 'integration',
    dependencies = [
        '3rdparty/intellij:sdk',
        '3rdparty/intellij:python-plugin',
        '3rdparty/intellij:scala-plugin',
        'jps-plugin',
        'src:pants-intellij-plugin',
        'testFramework:test-lib'
    ],
    sources = globs('com/twitter/intellij/pants/integration/*.java')
)

junit_tests(
    name = 'py-integration',
    dependencies = [
        '3rdparty/intellij:sdk',
        '3rdparty/intellij:python-plugin',
        '3rdparty/intellij:scala-plugin',
        'jps-plugin',
        'src:pants-intellij-plugin',
        'testFramework:test-lib'
    ],
    sources = globs('com/twitter/intellij/pants/integration/python/*.java')
)
