import { Component } from '@angular/core';
import { WeatherService } from 'src/app/service/weather.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent {

   city: string = '';
  weatherData: any = null;
  errorMessage: string = '';

  constructor(private weatherService: WeatherService) { }

  getWeather() {
    this.weatherData = null;
    this.errorMessage = '';

    if (!this.city.trim()) {
      this.errorMessage = "Please enter a city.";
      return;
    }

    this.weatherService.getWeather(this.city).subscribe({
      next: (data) => {
        // ✅ Map backend response to frontend object
        this.weatherData = {
          city: data.name,  // backend returns "name"
          country: data.sys.country,
          weatherDescription: data.weather[0].description,
          temperature: data.main.temp,
          humidity: data.main.humidity,
          windSpeed: data.wind.speed
        };

      },
      error: (err) => {
        console.error(err);
        this.errorMessage = "City not found or server error.";
      }
    });
  }


  }


