import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor() { }

  private CONFIG = {
    api: {
      mockLag: 0,
      mockMode: false,
      https: false,
      domain: 'localhost',
      baseUrl: 'api/',
      port: 8080,
    }
  };

  getBaseUrl(): string {
    const {https, domain, port, baseUrl} = this.CONFIG.api;
    return `${https ? 'https' : 'http'}://${domain}:${port}/${baseUrl}`;
  }

  isMockMode(): boolean {
    return this.CONFIG.api.mockMode;
  }

  getMockLag(): number {
    return this.CONFIG.api.mockLag;
  }
}
