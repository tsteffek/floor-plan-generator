```{html}
<type>(<scope>): <subject>

<body>

<footer>
```

## Type
Must be one of the following:

 - feat: A new feature
 - fix: A bug fix
 - docs: Documentation only changes
 - style: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
 - refactor: A code change that neither fixes a bug nor adds a feature
 - perf: A code change that improves performance
 - test: Adding missing or correcting existing tests
 - chore: Changes to the build process or auxiliary tools and libraries such as documentation generation

## Scope
The scope could be anything specifying place of the commit change. For example $location, $browser, $compile, $rootScope, ngHref, ngClick, ngView, etc...

You can use * when the change affects more than a single scope.

## Subject
The subject contains succinct description of the change:

 - use the imperative, present tense: "change" not "changed" nor "changes"
 - don't capitalize first letter
 - no dot (.) at the end

## Body
Just as in the subject, use the imperative, present tense: "change" not "changed" nor "changes". The body should include the motivation for the change and contrast this with previous behavior.

## Footer
The footer should contain any information about Breaking Changes and is also the place to reference GitHub issues that this commit closes.
