import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class DrawerService {
    private isOpenSubject = new BehaviorSubject<boolean>(false);

    isDrawerOpen$: Observable<boolean> = this.isOpenSubject.asObservable();

    // 開啟側邊欄
    open():void {
        this.isOpenSubject.next(true);
    }

    // 關閉側邊欄
    close(): void{
        this.isOpenSubject.next(false);
    }

    // 切換狀態
    toggle(): void {
        this.isOpenSubject.next(!this.isOpenSubject.value);
    }
}