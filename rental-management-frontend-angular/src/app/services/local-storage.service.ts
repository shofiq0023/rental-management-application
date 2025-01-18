import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private TOKEN: string = 'token';

  constructor() { }

  public isUserLoggedIn(): boolean {
    if (this.getItem(this.TOKEN) == undefined || this.getItem(this.TOKEN) == '') {
      console.log("LOGGED OUT!");
      return false;
    }

      console.log("LOGGED IN!");
      return true;
  }

  public setItem(key: string, value: string | undefined): void {
    localStorage.setItem(key, value == undefined ? '' : value);
  }

  public getItem(key: string): string {
    let localData: string | null = localStorage.getItem(key);

    if (localData == null) {
      return "";
    }

    return localData;
  }

  public removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  public clear(): void {
    localStorage.clear();
  }
}
