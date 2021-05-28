# esiea-3A-mobile 

## Android application project in kotlin displaying daily Covid data of the French regions and departements from [CoronavirusAPI-France](https://github.com/florianzemma/CoronavirusAPI-France)
### Run with *Android Studio* (apk available soon)  

  
Architecure follows **MVVM** model as Google recommendation.
Using **Retrofit2** to make api calls and **Room database** for data persitence, both interfaces wrapped in a *repository*. 
A *recycler view* diplays the first list with an add to favourite feature to keep some rows on top.
**SOLID** principles are followed thanks to *Singletons*, *Adapter*, and design patterns implementation.  
Additional librairies: **Picasso** to load images and **Gson** to parse the api response. Date and Time manipulation.

___
Scheduled features:
+ *push notifications* to notify updated data on API (at 20h everyday) with Firebase
+ *search bar* over the recylcerview to search for a location directly  

<br>
<h3>Looking like this</h3>
<table>
  <tr>
    <td>First Screen</td>
    <td>Details Screen</td>
    <td>Error Screen</td>
  </tr>
  <tr>
    <td><img src="screenshots/main-screen.png?raw=true" width="220px" alt="main screen"></td>
    <td><img src="screenshots/details-screen.png?raw=true" width="220px" alt="details screen"></td>
    <td><img src="screenshots/error-screen.png?raw=true" width="220px" alt="error screen"></td>
  </tr>
</table>


###### V1.0.0
With loading progression and error screen showing up when necessary, data is fetched online only if local version is outdated.  
Stars are clickable to save as favourite location for easier access (probably not persistent for the moment).  
Tests implementation is planned.

