using AutoMapper;
using Luxshare.Api.Entities;
using Luxshare.Api.Entities.Enums;
using Luxshare.Api.Entities.Report;
using Luxshare.Api.Extensions;
using Luxshare.Api.Extensions.AuthContext;
using Luxshare.Api.Extensions.CustomException;
using Luxshare.Api.Models.Response;
using Luxshare.Api.RequestPayload.Report.${entityName};
using Luxshare.Api.ViewModels.Report.${entityName};
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Data.SqlClient;

namespace Luxshare.Api.Controllers.Api.V1.Rbac
{
    /// <summary>
    /// 
    /// </summary>
    //[CustomAuthorize]
    [Route("api/v1/rbac/[controller]/[action]")]
    [ApiController]
    [CustomAuthorize]
    public class ${entityName}Controller : ControllerBase
    {
        private readonly MasterDbContext _masterContext;
        private readonly IMapper _mapper;

        /// <summary>
        /// 
        /// </summary>
        /// <param name="dbContext"></param>
        /// <param name="mapper"></param>
        public ${entityName}Controller(MasterDbContext masterContext, IMapper mapper)
        {
            _masterContext = masterContext;
            _mapper = mapper;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        [HttpPost]
        public IActionResult List(${entityName}RequestPayload payload)
        {
            using (_masterContext)
            {
                var query = _masterContext.${entityName}.AsQueryable();
                if (payload.IsDeleted > CommonEnum.IsDeleted.All)
                {
                    query = query.Where(x => x.IsDeleted == payload.IsDeleted);
                }
                var list = query.Paged(payload.CurrentPage, payload.PageSize).ToList();
                var totalCount = query.Count();
                var data = list.Select(_mapper.Map<${entityName}, ${entityName}JsonModel>);
                var response = ResponseModelFactory.CreateResultInstance;
                response.SetData(data, totalCount);
                return Ok(response);
            }
        }

        /// <summary>
        /// 创建菜单
        /// </summary>
        /// <param name="model">菜单视图实体</param>
        /// <returns></returns>
        [HttpPost]
        [ProducesResponseType(200)]
        public IActionResult Create(${entityName}CreateViewModel model)
        {
            using (_masterContext)
            {
                var entity = _mapper.Map<${entityName}CreateViewModel, ${entityName}>(model);
                entity.Guid = Guid.NewGuid();
            <#list columns as column>
                entity.${column.columnName} = model.${column.columnName};
            </#list>
                entity.CreatedOn = DateTime.Now;
                if (AuthContextService.CurrentUser != null)
                {
                    entity.CreatedByUserGuid = AuthContextService.CurrentUser.Guid;
                    entity.CreatedByUserName = AuthContextService.CurrentUser.DisplayName;
                }
                entity.IsDeleted = CommonEnum.IsDeleted.No;
                _masterContext.${entityName}.Add(entity);
                _masterContext.SaveChanges();
                var response = ResponseModelFactory.CreateInstance;
                response.SetSuccess();
                return Ok(response);
            }
        }

    }

}