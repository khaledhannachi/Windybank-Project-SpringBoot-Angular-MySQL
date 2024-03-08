import { Component, AfterViewInit } from '@angular/core';
import { Chart, ChartConfiguration, ChartTypeRegistry, GridLineOptions } from 'chart.js/auto';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html'
})
export class LineChartComponent implements AfterViewInit {

  constructor() { }

  ngAfterViewInit() {
    const config: ChartConfiguration<keyof ChartTypeRegistry, number[], string> = {
      type: 'line',
      data: {
        labels: [
          "January",
          "February",
          "March",
          "April",
        ],
        datasets: [
          {
            label: new Date().getFullYear().toString(), // Convert to string
            backgroundColor: "#4c51bf",
            borderColor: "#4c51bf",
            data: [0, 14, 8,20 ],
            fill: false
          },
          {
            label: (new Date().getFullYear() - 1).toString(), // Convert to string
            fill: false,
            backgroundColor: "#ed64a6",
            borderColor: "#ed64a6",
            data: [0, 6, 14, 24]
          }
        ]

      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        scales: {
          x: {
            ticks: {
              color: 'rgba(255,255,255,.7)'
            },
            display: true,
            grid: {
              display: false, // Remove zero line
              color: 'rgba(255, 255, 255, 0.15)'
            }
          },
          y: {
            ticks: {
              color: 'rgba(255,255,255,.7)'
            },
            display: true,
            grid: {
              display: false, // Remove zero line
              color: 'rgba(255, 255, 255, 0.15)'
            }
          }
        }
      }
    };

    const ctx: any = document.getElementById("line-chart");
    const chart = new Chart(ctx, config);
  }
}
