# UFO2

# Exercise 1

## Rule report can be found here:
- https://pastebin.com/PWJ8bmGs

# Rulechecks

## Comments 
- Comments were chosen due to the age of the project. Alot of methods and handlers aren't commented so the code can be difficult to navigate through

## Coverage
- Only a small margin of the code has actually been tested, due to the experience of the coders at the time.

## Comment Quality
- Despite the lack of comments, the comments which are present are too long and incostistantly written.

## Code Quality
 - The project is badly written. With very limited experience with sql and java as a whole, alot of the code could easilly be optimized better or made more readable

## Optimization
- Alot of calls are made with the DB Connector library, which can add up to 100 ms response time to each call or request to the server. Using local storage or some sort of state could help here so it isn't needed to call the database when it's uneeded.

## Unused Code
- Alot of project code has been dummy code and isn't used at all, and was used to debug. This was left in.

## Security
- Recent years alot of site has been suffering from sql injections. This would be the case here aswell. We don't assert values which the library supports, we concat strings.

## Naming Schemes
- Naming schemes were somewhat random, there weren't any systematic naming schemes during this project.

## Coupling
- The project isn't very modular in itself, it's very static. Some classes could be seperated and have higher order classes to handle the handlers.

## Error handling
- Exceptions aren't properly handled, and so  they aren't showed to the user very often as they're just printed out on the backend.

# Exercise 2

- http://prntscr.com/l464tm

Using JSP at the time, since we didn't know much better, we assumed it was well written and somewhat light weight due to the popularity of JSP and Java at the time. Although as the picture shows, the threadpool and apache itself is really performance hefty as it keeps the threadpool checked, whereas newer frameworks are more lightweight and scale better with the help of other services. 