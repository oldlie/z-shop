import { Injectable } from '@angular/core';
import { CoreService } from './core.service';
import { BaseResponse } from '../model/response';

@Injectable()
export class FrontService {

    constructor(private coreService: CoreService) { }
}
