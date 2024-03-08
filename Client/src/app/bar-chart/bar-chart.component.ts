import { Component, AfterViewInit } from '@angular/core';
import { Chart, ChartConfiguration, Color } from 'chart.js';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html'
})
export class BarChartComponent implements AfterViewInit {

  constructor() { }

  ngAfterViewInit() {
    const config: ChartConfiguration<'bar'> = {
      type: 'bar',
      data: {
        labels: [
          'January',
          'February',
          'March'
        ],
        datasets: [
          {
            label: `${new Date().getFullYear()}`,
            backgroundColor: '#ed64a6',
            borderColor: '#ed64a6',
            data: [0, 14, 8,20 ],
            barThickness: 8
          },
          {
            label: `${new Date().getFullYear() - 1}`,
            backgroundColor: '#4c51bf',
            borderColor: '#4c51bf',
            data: [0, 7, 18, 42],
            barThickness: 8
          }
        ]
      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        plugins: {
          legend: {
            labels: {
              color: 'rgba(0,0,0,.4)' as Color // Cast to Color
            },
            align: 'end',
            position: 'bottom'
          },
          tooltip: {
            mode: 'index',
            intersect: false
          }
        },
        scales: {
          x: {
            display: false,
            title: {
              display: true,
              text: 'Month'
            },
            grid: {
              display: false,
              color: 'rgba(33, 37, 41, 0.3)'
            }
          },
          y: {
            title: {
              display: false,
              text: 'Value'
            },
            grid: {
              color: 'rgba(33, 37, 41, 0.2)'
            }
          }
        }
      }
    };

    const ctx: any = document.getElementById('bar-chart');
    new Chart(ctx, config);
  }

}
